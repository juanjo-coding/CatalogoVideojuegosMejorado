fun main() {
    ConexionBD.testConnection()

    // Listando los videojuegos
    println("Mostrando catalogo de videojuegos:")
    VideojuegoDAO.listarVideojuegos().forEach {
        println(" - [${it.id}] ${it.titulo} (${it.genero}), anyo: ${it.anio}, nota: ${it.nota}")
    }

    // Buscando videojuego por ID
    val videojuego = VideojuegoDAO.consultarPorId(3)
    if (videojuego != null) {
        println("Videojuego encontrado: [${videojuego.id}] [${videojuego.titulo}] ${videojuego.anio} (${videojuego.nota})")
    } else {
        println("Videojuego no encontrado")
    }

    // Insertando nuevo videojuego al catalogo
    VideojuegoDAO.insertarVideojuego(
        Videojuego(
            titulo = "Elden Ring",
            genero = "Souls-like",
            anio = 2022,
            nota = 9.6
        )
    )

    // Actualizando la nota de un videojuego ya existente
    VideojuegoDAO.actualizarVideojuego(
        Videojuego(
            id = 2,
            titulo = "Red Dead Redemption 2",
            genero = "Acción",
            anio = 2018,
            nota = 10.0
        )
    )

    // Eliminando un videojuego de la lista (Celeste)
    VideojuegoDAO.eliminarVideosjuego(5)
}