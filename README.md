#  TikiData — Plataforma Integral de Fútbol

TikiData es una plataforma backend orientada a los aficionados al fútbol. Permite la gestión centralizada de información futbolística e integra funcionalidades interactivas como seguimiento de resultados, estadísticas, minijuegos y foros de discusión comunitaria.

---

##  Tecnologías utilizadas

| Tecnología | Versión |
|---|---|
| Java | 25 (Loom EA) |
| Spring Boot | 4.0.6 |
| Spring Security | Integrado |
| Spring Data JPA | Integrado |
| Spring Validation | Integrado |
| Base de datos | H2 (en memoria) |
| Lombok | Última estable |
| Maven | Gestor de dependencias |

---

##  Estructura del proyecto

```
src/
├── main/
│   └── java/com/TikiData/platform/
│       ├── Player/
│       │   ├── Controller/
│       │   ├── Service/
│       │   ├── Repository/
│       │   └── Model/
│       ├── Team/
│       ├── Championship/
│       ├── News/
│       ├── Community/
│       ├── Game/
│       └── Common/
│           ├── Config/
│           └── Exception/
```

---

##  Roles del sistema

###  Usuario
Rol orientado al consumo de datos, personalización y participación. Puede:
- Consultar información de equipos, jugadores y campeonatos
- Seguir resultados en vivo
- Crear listas de equipos favoritos
- Consultar estadísticas
- Participar en foros de discusión
- Interactuar con minijuegos

###  Administrador
Rol orientado a la gestión y mantenimiento del sistema. Puede:
- Realizar altas, bajas y modificaciones (ABM) de usuarios
- Gestionar equipos, campeonatos y noticias
- Controlar la calidad de los datos del sistema
- Acceder a todos los endpoints de la plataforma

---

##  Seguridad

El sistema implementa autenticación y autorización mediante **Spring Security**:

- Todos los endpoints están protegidos
- Autenticación básica (usuario y contraseña)
- Control de acceso basado en roles (`ADMIN`, `USER`)
- Los endpoints de gestión están restringidos exclusivamente al rol `ADMIN`

---

##  Entidades principales

- **Jugador (Player)** — nombre, número, posición, fecha de nacimiento, equipo
- **Equipo (Team)** — nombre, escudo, país, jugadores
- **Campeonato (Championship)** — nombre, temporada, equipos participantes
- **Noticia (News)** — título, contenido, fecha de publicación
- **Comunidad (Community)** — foros de discusión entre usuarios

---

##  Cómo ejecutar el proyecto

### Requisitos previos
- Java 21 o superior
- Maven instalado

### Pasos

1. Cloná el repositorio:
```bash
git clone https://github.com/tu-usuario/tikidata.git
cd tikidata
```

2. Compilá el proyecto:
```bash
mvn clean install
```

3. Ejecutá la aplicación:
```bash
mvn spring-boot:run
```

4. La API estará disponible en:
```
http://localhost:8080
```

5. Consola H2 (base de datos en memoria):
```
http://localhost:8080/h2-console
```

---

##  Endpoints principales

### Jugadores
| Método | Endpoint | Descripción | Rol requerido |
|---|---|---|---|
| GET | `/api/players` | Listar todos los jugadores | USER, ADMIN |
| GET | `/api/players/{id}` | Obtener jugador por ID | USER, ADMIN |
| GET | `/api/players/name?name=` | Buscar jugador por nombre | USER, ADMIN |
| POST | `/api/players` | Crear jugador | ADMIN |
| PUT | `/api/players/{id}` | Actualizar jugador | ADMIN |
| DELETE | `/api/players/{id}` | Eliminar jugador | ADMIN |

### Equipos
| Método | Endpoint | Descripción | Rol requerido |
|---|---|---|---|
| GET | `/api/teams` | Listar todos los equipos | USER, ADMIN |
| GET | `/api/teams/{id}` | Obtener equipo por ID | USER, ADMIN |
| POST | `/api/teams` | Crear equipo | ADMIN |
| PUT | `/api/teams/{id}` | Actualizar equipo | ADMIN |
| DELETE | `/api/teams/{id}` | Eliminar equipo | ADMIN |

### Campeonatos
| Método | Endpoint | Descripción | Rol requerido |
|---|---|---|---|
| GET | `/api/championships` | Listar campeonatos | USER, ADMIN |
| POST | `/api/championships` | Crear campeonato | ADMIN |
| PUT | `/api/championships/{id}` | Actualizar campeonato | ADMIN |
| DELETE | `/api/championships/{id}` | Eliminar campeonato | ADMIN |

### Noticias
| Método | Endpoint | Descripción | Rol requerido |
|---|---|---|---|
| GET | `/api/news` | Listar noticias | USER, ADMIN |
| GET | `/api/news/{id}` | Obtener noticia por ID | USER, ADMIN |
| POST | `/api/news` | Crear noticia | ADMIN |
| PUT | `/api/news/{id}` | Actualizar noticia | ADMIN |
| DELETE | `/api/news/{id}` | Eliminar noticia | ADMIN |

---

##  Manejo de excepciones

El sistema cuenta con un manejador global de excepciones (`@RestControllerAdvice`) que devuelve respuestas HTTP apropiadas:

| Excepción | Código HTTP |
|---|---|
| `PlayerNotFoundException` | 404 Not Found |
| `TeamNotFoundException` | 404 Not Found |
| `NewsNotFoundException` | 404 Not Found |
| `ResourceNotFoundException` | 404 Not Found |
| `ResourceAlreadyExistsException` | 409 Conflict |
| `MethodArgumentNotValidException` | 400 Bad Request |

---

##  Autores

Proyecto desarrollado como trabajo final para la materia **Programación 3** — TUP UTN.

---

##  Licencia

Este proyecto es de uso académico.