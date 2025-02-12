public class Hero{
    private String name;
    private int hitPoints;
    public Hero(String name){
        this.name=name;
        hitPoints=100;
    }
    public String getName(){
        return name;
    }
    public int getHitPoints(){
        return hitPoints;
    }
    public String toString(){
        return "Hero{name='"+name+"', hitPoints="+hitPoints+"}";
    }
    public void senzuBean(String name){
        hitPoints=100;
    }
    public void attack(Hero opponent){
        double num=Math.random();
        if(num<0.5){
            opponent.hitPoints-=10;
        }
        if(num>=0.5){
            this.hitPoints-=10;
        }
    }
    private void fightUntilTheDeathHelper(Hero opponent){
        while (this.hitPoints > 0 && opponent.getHitPoints() > 0) {
            this.attack(opponent);
            if (opponent.getHitPoints() > 0) {
                opponent.attack(this);
            }
        }
    }
    public String fightUntilTheDeath(Hero opponent){
        this.senzuBean(this.getName());
        opponent.senzuBean(opponent.getName());
        this.fightUntilTheDeathHelper(opponent);
        return this.getName()+": "+this.getHitPoints()+"  "+opponent.getName()+": "+opponent.getHitPoints();
    }
    private int[] nFightsToTheDeathHelper(Hero opponent, int n){
        int[] wins=new int[n];
        while(n>0){
            int i=0;
            if(this.hitPoints!=0||opponent.hitPoints!=0){
                this.attack(opponent);
            }
            if(this.getHitPoints()==0){
                wins[i]=1;
            }
            if(opponent.getHitPoints()==0){
                wins[i]=0;
            }
            this.senzuBean(this.getName());
            opponent.senzuBean(opponent.getName());
            n--;
            i++;
        }
        return wins;
    }
    public String nFightsToTheDeath(Hero opponent, int n){
        int[] wins=this.nFightsToTheDeathHelper(opponent, n);
        int myWins=0;
        int oppWins=0;
        for(int i=0;i<n;i++){
            if(wins[i]==1){
                myWins++;
            }
            if(wins[i]==0){
                oppWins++;
            }
        }
        if(myWins>oppWins){
            return this.getName()+": "+myWins+" wins\n"+opponent.getName()+": "+oppWins+" wins\n"+this.getName()+" wins!";
        }
        if(myWins<oppWins){
            return this.getName()+": "+myWins+" wins\n"+opponent.getName()+": "+oppWins+" wins\n"+opponent.getName()+" wins!";
        }
        else{
            return this.getName()+": "+myWins+" wins\n"+opponent.getName()+": "+oppWins+" wins\n"+"OMG! It was actually a draw!";
        }
    }
    public void dramaticFightToTheDeath(Hero opponent) throws InterruptedException {
        this.senzuBean(this.getName());
        opponent.senzuBean(opponent.getName());
        while (this.hitPoints > 0 && opponent.getHitPoints() > 0) {
            this.attack(opponent);
            wait(1000);
            System.out.println(this.getName() + ": " + this.getHitPoints() + "  " + opponent.getName() + ": " + opponent.getHitPoints());
            if (opponent.getHitPoints() > 0) {
                opponent.attack(this);
                wait(1000);
                System.out.println(this.getName() + ": " + this.getHitPoints() + "  " + opponent.getName() + ": " + opponent.getHitPoints());
            }
        }
    }
}
