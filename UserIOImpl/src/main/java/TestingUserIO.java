public class TestingUserIO {
    public static void main(String[] args) {

        UserIO io = new UserIOConsoleImpl();

        int smallNum = io.readInt("Give me a small number :");
        int bigNum = io.readInt("Now give me a much bigger number! :");
        if(bigNum < smallNum){
            io.print("Hey! " + smallNum +" is BIGGER than " + bigNum);
            io.print("I guess I can fix it.");
            int swapNum = bigNum;
            bigNum = smallNum;
            smallNum = swapNum;
        }
        int betweenNum = io.readInt("Now give me one in between! : ", smallNum, bigNum);
        io.print("I like the number "+betweenNum+"!");
    }
}


