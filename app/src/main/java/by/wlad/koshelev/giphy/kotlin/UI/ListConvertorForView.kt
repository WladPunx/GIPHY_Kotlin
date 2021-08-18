package by.wlad.koshelev.giphy.kotlin.UI

import androidx.lifecycle.MutableLiveData

class ListConvertorForView<T> {

    /**
     * ИДЕЯ КЛАССА
     *
     * 0. создаем экземпляр класса
     *
     * 1. на вход подаем массив и указываем по сколько их надо разбивать "fun new()"
     *      он скидывает текущую страницу на 1. считает МаксСтраниц. и вызывает метод "показать первую страницу"
     *
     * 2. выходной массив в виде LiveData. подписываемся на его изменения
     *
     * 3. переключаем страницы с помощью метода "fun setNumber()"
     *
     * 4. number как LiveData чтобы показывать текущую страницу
     *
     * 5. maxNumber для вкл/откл возможности переходить на следующусю страницу, если она есть / ее нет
     */

    private var size: Int = 0 // размер кластеров

    private var input: MutableList<T> = mutableListOf() // входной массив
    var output: MutableLiveData<MutableList<T>> = MutableLiveData(mutableListOf()) // выходный массив для текущей страницы

    var number: MutableLiveData<Int> = MutableLiveData(1) // текущая страница
    var maxNumber: Int = 1 // максимальное кол-во странийц


    /**
     * метод новых данных
     */
    fun new(newInput: MutableList<T>, newSize: Int) {
        input = newInput // меняем входные данные
        size = newSize
        calcMaxNumber() // пересчитываем МАКС страниц
        number.value = 1 // текущая страница на 1
        setViewGifList(1) // отображение
    }

    /**
     * перейти на нужную страницу
     */
    fun setNumber(i: Int) {
        number.value = i
        setViewGifList(i)
    }


    /**
     * расчет данных для страницы
     */
    private fun setViewGifList(batch: Int) {
        output.value?.clear()
        val min: Int = (batch - 1) * size
        val max: Int = min + size
        for (i in min until max) {
            try {
                val a: T = input[i]
                output.value?.add(a)
            } catch (ex: Exception) {
            }
        }
        output.value = output.value
    }


    /**
     * сколько у нас всего страниц?
     */
    private fun calcMaxNumber() {
        maxNumber = input.size / 10
        if (input.size % 10 != 0) maxNumber++
    }


}