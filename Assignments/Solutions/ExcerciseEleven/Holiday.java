public class Holiday {
    private String name;
    private int day;
    private String month;

    //Answer A
    public Holiday(String n, int d, String m) {
        name = n;
        day = d;
        month = m;
    }

    //Answer B
    //The method only needs one parameter since it already has access to the current Holiday object
    //It then compares this Holiday's month to the parameter Holiday's month
    public boolean inSameMonth(Holiday hol){
        return this.month.equals(hol.month);
    }

    //Answer C
    public static double avgDate(Holiday[] hol) {
        int sum = 0;
        for(int i = 0; i < hol.length; i++) {
            sum = sum + hol[i].day;
        }
        return ((double) sum)/hol.length;
    }
}
