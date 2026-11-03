package com.example.b16reciclerview

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    //instancia que conecta con el repo que provee la info
    private val countriesRepository = Countries()

    //almacena la lista actual de paises que empieza vacia y solo el viewModel puede modificarla
    private val _paises = MutableStateFlow<List<Pais>>(emptyList())
    //enseña el estado de la lista, asi la interfaz puede ver cuando hay cambios pero no cambiarla
    val paises: StateFlow<List<Pais>> = _paises.asStateFlow()

    //carga la lista inicial de países obtenida desde el repositorio y se la asigna a _paises.value, poblando la pantalla por primera vez.
    init {
        _paises.value = countriesRepository.nameList
    }

    fun eliminarPais(posicion: Int) { //recibe indice de la posicion a borrar
        val listaActual = _paises.value.toMutableList() //convierte la lista a mutable para poder manipularla
        if (posicion in listaActual.indices) { //verifica que ese indice existe, luego elimina elemento y reasigna
            listaActual.removeAt(posicion)
            _paises.value = listaActual
        }
    }

    //funcion que llama al repo para añadir los paises que pongan
    fun agregarPais(textoBusqueda: String): ResultadoAgregar {
        val paisEncontrado = countriesRepository.buscar(textoBusqueda)

        //si no encuentraa pais devuelve que no existe
        return when {
            paisEncontrado == null -> {
                ResultadoAgregar.NoEncontrado(textoBusqueda)
            }
            _paises.value.any { it.nombre == paisEncontrado.nombre } -> {
                ResultadoAgregar.YaExiste(paisEncontrado.nombre) //devuelve que ya existe
            }
            else -> { //crea una copia mutable y añade el pais
                val listaActual = _paises.value.toMutableList()
                listaActual.add(paisEncontrado)
                _paises.value = listaActual
                ResultadoAgregar.Exito(paisEncontrado)
            }
        }
    }
}

//permite que la actividad que llama a agregarPais reciba respuesta
sealed class ResultadoAgregar {
    data class Exito(val pais: Pais) : ResultadoAgregar()
    data class NoEncontrado(val query: String) : ResultadoAgregar()
    data class YaExiste(val nombrePais: String) : ResultadoAgregar()
}