package br.com.wagner.awsproject01.handler

import java.lang.RuntimeException

class ResourceNotFoundException(val msg: String): RuntimeException(msg) {
}