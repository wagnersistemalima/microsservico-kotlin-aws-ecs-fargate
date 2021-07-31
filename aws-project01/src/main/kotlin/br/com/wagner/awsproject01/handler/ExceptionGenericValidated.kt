package br.com.wagner.awsproject01.handler

import java.lang.RuntimeException

class ExceptionGenericValidated(val msg: String): RuntimeException(msg) {
}