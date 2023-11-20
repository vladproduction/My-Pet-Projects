package net.app;

public class Main02 {
    public static void main(String[] args) {
        Parent parent = getParent(2);
        parent.speak();
    }
    private static Parent getParent(int type){
        Parent x = null;
        if(type==0){
            x=new Parent();
        }
        if(type==1){
            x=new Child();
        }
        if(type==2){
            x=new Child2();
        }

        return x;
    }
}
