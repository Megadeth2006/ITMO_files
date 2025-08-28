rootProject.name = "lab7"
include(":client")
project(":client").projectDir = file("src/client")

include(":server")
project(":server").projectDir = file("src/server")

include(":common")
project(":common").projectDir = file("src/common")
