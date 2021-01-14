package New_Calculator;

public class Cleaner_AC extends SimpleCaculator{
    public Cleaner_AC() {
        text.setText(" ");
        flg = 1;
        while(num_stack.size()!=0)
            num_stack.pop();
        while(str_stack.size()!=0)
            str_stack.pop();
    }
}
