package jvm_test.JvmMemTest;


/**
 * 测试栈溢出 StackOverflowError 栈帧溢出
 *           OutOfMemoryError 内存溢出
 */
public class StackErrorMock {

    int pushStackNum = 0;

    public static void main(String[] args) {
        StackErrorMock stackErrorMock = new StackErrorMock();

        try {
            stackErrorMock.pushStack();
        } catch (Throwable e) {

            System.out.println("入栈数：" + stackErrorMock.pushStackNum);
            System.out.println(e.getMessage());
        }

    }

    void pushStack(){
        pushStackNum ++;
        pushStack();
    }
}
