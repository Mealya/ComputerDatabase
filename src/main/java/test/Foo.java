package test;

public interface Foo {
    public default void foo() {
        System.out.println("Default implementation of foo()");
    }
}