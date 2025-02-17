storage "file" {
  path = "/vault/data"
}

listener "tcp" {
  address     = "[::]:8200"
  tls_disable = true
}

disable_mlock = true

ui = true
