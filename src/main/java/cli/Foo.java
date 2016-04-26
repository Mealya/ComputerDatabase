package cli;

public interface Foo {
    default void foo() {
        System.out.println("Default implementation of foo()");
    }
}