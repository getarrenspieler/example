package ru.example.threads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.function.Function;

public class ThreadsafeComputer<K, V> {

    private final ConcurrentHashMap<K, Future<V>> m = new ConcurrentHashMap<>();

    /**
     * Возвращает future для вычисления функции f на заданном ключе k.
     * <p>
     * Если значение для такого ключа уже вычислялось, то вычисление повторно не происходит, и возвращается
     * уже вычисленное значение.
     * <p>
     * Вычисленное значение возвращается независимо от того, какая функция была передана при повторном вызове. Т.о.
     * при повторном вызове с одним и тем же ключом функция игнорируется.
     *
     * @param k ключ
     * @param f функция вычисления значения
     * @return  future для вычисления на заданной функции
     *
     * @throws NullPointerException если {@code  k == null}
     */
    public Future<V> compute(K k, Function<K, V> f) {
        return m.computeIfAbsent(k, key -> CompletableFuture.supplyAsync(() -> f.apply(key)));
    }
}
