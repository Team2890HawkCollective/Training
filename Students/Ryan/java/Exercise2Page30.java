public class Exercise2Page30 {
    public static void main(String args[]){
        int inputYear = 400; 
        boolean yearReturn;
        if(inputYear % 4 == 0)
        {
            if(inputYear % 100 == 0)
            {
                if(inputYear % 400 == 0)
                {
                    yearReturn = true;
                }
                else{yearReturn = false;}
            }
            else{yearReturn = true;}
        }
        else{yearReturn = false;}

        System.out.println("inputYear is a leap year? " + yearReturn);
    }
}
