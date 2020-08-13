package java_base.list;

public class ArrayStack<T> {

    int n = 0;
    private Object[] elements;

    public ArrayStack(int n) {
        this.elements = new Object[n];
    }


    public int push(T t) {
        if (n > elements.length - 1) {
            throw new RuntimeException("栈溢出");
        }
        elements[n] = t;
        n ++;
        return 1;
    }

    public T pop() {
        if (n == 0) {
            return null;
        }else {
            n --;
            return (T) elements[n];
        }
    }

    public void show() {
        for (int i = 0; i < n; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        ArrayStack<String> arrayStack = new ArrayStack<>(5);
        arrayStack.push("1");
        arrayStack.show();
        arrayStack.push("2");
        arrayStack.push("3");
        arrayStack.show();
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        arrayStack.show();
        arrayStack.push("2");
        arrayStack.push("3");
        arrayStack.push("4");
        arrayStack.push("5");
        arrayStack.push("6");
    }
}
