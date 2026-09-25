import requests

# 1. Definición de los Estados del Autómata (Q)
ESTADO_MENU = 0
ESTADO_RASTREO_GUIA = 1
ESTADO_NOVEDAD_GUIA = 2
ESTADO_NOVEDAD_UBICACION = 3
ESTADO_NOVEDAD_DESCRIPCION = 4

# 2. Estado Inicial (q0) y memoria temporal
estado_actual = ESTADO_MENU
memoria_transicion = {}

def mostrar_menu():
    print("\n" + "="*40)
    print("🤖 MENU PRINCIPAL - LogiTrack")
    print("1. 📦 Rastrear un paquete")
    print("2. ⚠️ Reportar una novedad")
    print("3. ❌ Salir")
    print("="*40)

print("🤖 Bot Logístico: ¡Hola! Soy tu asistente de envíos.")
mostrar_menu()

# 3. Ciclo de procesamiento (Función de Transición δ)
while True:
    entrada = input("\n👤 Tú: ").strip()
    
    # Condición de salida global
    if entrada.lower() in ['salir', 'exit', 'quit', '3'] and estado_actual == ESTADO_MENU:
        print("🤖 Bot Logístico: ¡Hasta pronto! Que tus envíos lleguen a tiempo.")
        break

    # -- TRANSICIONES DESDE EL MENÚ --
    if estado_actual == ESTADO_MENU:
        if entrada == '1':
            print("🤖 Bot Logístico: Perfecto. Por favor, ingresa tu número de guía (ej: LOG-12345).")
            estado_actual = ESTADO_RASTREO_GUIA
        elif entrada == '2':
            print("🤖 Bot Logístico: Entendido. Vamos a registrar un problema. Ingresa el número de guía afectado.")
            estado_actual = ESTADO_NOVEDAD_GUIA
        else:
            print("🤖 Bot Logístico: ❌ Opción no válida. Por favor, ingresa 1, 2 o 3.")
            mostrar_menu()
            
    # -- FLUJO 1: RASTREO (GET) --
    elif estado_actual == ESTADO_RASTREO_GUIA:
        print("🤖 Bot Logístico: Buscando paquete en la base de datos...")
        try:
            url = f"http://localhost:8080/api/bot/rastreo"
            respuesta = requests.get(url, params={'guia': entrada})
            print(f"\n{respuesta.text}")
        except requests.exceptions.RequestException:
            print("\n🤖 Bot Logístico: ❌ Error. Asegúrate de que el servidor Java esté corriendo.")
        
        # Transición de retorno al estado inicial
        estado_actual = ESTADO_MENU
        mostrar_menu()
        
    # -- FLUJO 2: REPORTE DE NOVEDADES (POST) --
    elif estado_actual == ESTADO_NOVEDAD_GUIA:
        memoria_transicion['guia'] = entrada
        print("🤖 Bot Logístico: ¿En qué ciudad o ubicación ocurrió la novedad? (ej: Fusagasugá)")
        estado_actual = ESTADO_NOVEDAD_UBICACION
        
    elif estado_actual == ESTADO_NOVEDAD_UBICACION:
        memoria_transicion['ubicacion'] = entrada
        print("🤖 Bot Logístico: Por favor, describe brevemente el problema (ej: Retraso por cierre vial).")
        estado_actual = ESTADO_NOVEDAD_DESCRIPCION
        
    elif estado_actual == ESTADO_NOVEDAD_DESCRIPCION:
        memoria_transicion['descripcion'] = entrada
        print("🤖 Bot Logístico: Conectando con el servidor para guardar el reporte...")
        try:
            url = "http://localhost:8080/api/bot/novedad"
            # Enviamos los datos recolectados en los estados anteriores
            respuesta = requests.post(url, params=memoria_transicion)
            print(f"\n{respuesta.text}")
        except requests.exceptions.RequestException:
            print("\n🤖 Bot Logístico: ❌ Error. Asegúrate de que el servidor Java esté corriendo.")
        
        # Limpiamos la memoria y retornamos al estado inicial
        memoria_transicion.clear()
        estado_actual = ESTADO_MENU
        mostrar_menu()