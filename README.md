# Test case for Andiks

## Test task 1
Есть некоторая внешняя библиотека ExtLib, она предоставляет метод для вычисления указанной степени заданного числа, например, extLib.eval(a, p).
где a - некоторые положительные число,
p - степень, в которую необходимо возвести число a

Наша система работает с данной библиотекой в некотором методе evaluate(data, p)
 где data - массив целых положительных чисел,
 p - степень, в которую нужно возвести каждое из чисел в массиве

Все числа целочисленные.
Допускается лишь выделение массива размером, равным исходному для сохранения результатов.

Проблема в том, что наш метод evaluate работает очень медленно и библиотека была использована на однопроцессорной системе в однопоточном приложении.

Мы купили сервер с Х ядрами, но запустив на нем свое приложение не получили прироста прозводительности, но нам бы хотелось чтобы наше приложение использовало все ядра процессора для вычислений eval(a,p)

Задача: Модифицировать реализацию метода evaluate таким образом, чтобы возвращался вычисленный параллельно на Х ядрах массив data.  Использовать можно только JDK 7

 #### Run ru.buyanov.andiks.concurrency.EvaluatorTest

## Test task 2

Please implement this method to
          return the number of different combinations of US coins
          (penny: 1c, nickel: 5c, dime: 10c, quarter: 25c, half-dollar: 50c)
          which may be used to produce a given amount of money.

  For example, 11 cents can be produced with
  one 10-cent coin and one 1-cent coin,
  two 5-cent coins and one 1-cent coin,
  one 5-cent coin and six 1-cent coins,
  or eleven 1-cent coins.
  So there are four unique ways to produce 11 cents.
  Assume that the cents parameter is always positive.
 #### Run ru.buyanov.andiks.cents.CentsTest
