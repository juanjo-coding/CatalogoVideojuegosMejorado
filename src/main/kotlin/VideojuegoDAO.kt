object VideojuegoDAO {
    fun listarVideojuegos(): List<Videojuego> {
        val lista = mutableListOf<Videojuego>()
        val sql = "SELECT * FROM videojuegos"
        ConexionBD.getConnection()?.use { conn ->
            conn.prepareStatement(sql).use { statement ->
                val rs = statement.executeQuery()
                while (rs.next()) {
                    lista.add(
                        Videojuego(
                            id = rs.getInt("id"),
                            titulo = rs.getString("titulo"),
                            genero = rs.getString("genero"),
                            anio = rs.getInt("anio"),
                            nota = rs.getDouble("nota")

                        )
                    )
                }
            }
        }
        return lista
    }

    fun consultarPorId(id: Int): Videojuego? {
        var videojuego: Videojuego? = null
        val sql = "SELECT * FROM videojuegos WHERE id = ?"
        ConexionBD.getConnection()?.use { conn ->
            conn.prepareStatement(sql).use { statement ->
                statement.setInt(1, id)
                val rs = statement.executeQuery()
                if (rs.next()) {
                    videojuego = Videojuego(
                        id = rs.getInt("id"),
                        titulo = rs.getString("titulo"),
                        genero = rs.getString("genero"),
                        anio = rs.getInt("anio"),
                        nota = rs.getDouble("nota")
                    )
                }
            }
        }
        return videojuego
    }

    fun insertarVideojuego(videojuego: Videojuego) {
        val sql = "INSERT INTO videojuegos(titulo,genero,anio,nota) VALUES (?,?,?,?)"
        ConexionBD.getConnection()?.use { conn ->
            conn.prepareStatement(sql).use { statement ->
                statement.setString(1, videojuego.titulo)
                statement.setString(2, videojuego.genero)
                statement.setInt(3, videojuego.anio)
                statement.setDouble(4, videojuego.nota)
                if (statement.executeUpdate() > 0) {
                    println("${videojuego.titulo} ha sido insertado con exito.")
                }
            }
        }
    }

    fun actualizarVideojuego(videojuego: Videojuego) {
        if (videojuego.id == null) {
            println("ERROR: No se puede actualizar un videojuego sin ID.")
            return
        }
        val sql = "UPDATE videojuegos SET titulo = ?, genero = ?, anio = ?, nota = ? WHERE id = ?"
        ConexionBD.getConnection()?.use { conn ->
            conn.prepareStatement(sql).use { statement ->
                statement.setString(1, videojuego.titulo)
                statement.setString(2, videojuego.genero)
                statement.setInt(3, videojuego.anio)
                statement.setDouble(4, videojuego.nota)
                statement.setInt(5, videojuego.id)
                if (statement.executeUpdate() > 0) {
                    println("El videojuego con el ID ${videojuego.id} ha sido actualizado con exito.")
                } else {
                    println("No se encontro ningun videojuego con el ID ${videojuego.id}.")
                }
            }
        }
    }

    fun eliminarVideosjuego(id: Int) {
        val sql = "DELETE FROM videojuegos WHERE id = ?"
        ConexionBD.getConnection()?.use { conn ->
            conn.prepareStatement(sql).use { statement ->
                statement.setInt(1, id)
                if (statement.executeUpdate() > 0) {
                    println("El videojuego con ID $id ha sido eliminado.")
                } else {
                    println("No se encontro ningun videojuego con el ID $id.")
                }
            }
        }
    }
}
