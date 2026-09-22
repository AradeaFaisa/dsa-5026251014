public class CarWash extends WashService{
    public CarWash(String id, int days){
        super(id, days);
    }

    @Override
    public int calculateCharge(){
        int days = getDays();
        int washingCost;
        if(days <= 3){
            washingCost = days*35000;
        }else{
            washingCost = (3*35000) + ((days-3)*25000);
        }

        return washingCost + 15000;
    }

    @Override
    public String label(){
        return "Car";
    }
}