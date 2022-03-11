package Source;

import java.awt.*;
import java.util.Random;

public class Monster extends Entity {
    //TODO
    private int health;
    public static double speed;
    private int x;
    private int y;
    public boolean isZigzag;
    boolean leftRight = true; //true = left, false = right
    boolean upBottom = true; //true = up, false = down
    private int hit;
    public IMonsterStrategy iMonsterStrategy;

    /**
     *
     */
    public Monster(){
        health = 100 + (Game.getInstance().waves)*20;
        speed = 1.0;
        Random random = new Random();
        int a = random.nextInt(2);
        if(a==1) isZigzag=true;
        else isZigzag=false;
    }

    /**
     *
     * @return IMonsterStrategy
     */
    public IMonsterStrategy getiMonsterStrategy() {
        return iMonsterStrategy;
    }

    /**
     *
     * @param iMonsterStrategy
     */
    public void setiMonsterStrategy(IMonsterStrategy iMonsterStrategy) {
        this.iMonsterStrategy = iMonsterStrategy;
    }

    public int getX() {
        return x;
    }

    /**
     *
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @return int
     */
    public int getHealth() {
        return health;
    }

    /**
     *
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     *
     */
    @Override
    public void step() {
        //TODO
        if(!isZigzag)
        {
            if(this.getY() == 30 && this.getX() < 330)
            {
                this.setX((this.getX())+1);
            }
            else if(this.getX() == 330 && this.getY() < 330)
            {
                this.setY((this.getY())+1);
            }
            else if(this.getY() == 330 && this.getX() > 30)
            {
                this.setX((this.getX())-1);
            }
            else if(this.getX() <= Commons.StartWidth && this.getY() > 30)
            {
                this.setY((this.getY())-1);
            }
        }
        else
        {
            int state = 0;
            if(this.getX()+Commons.MonsterSize <= 100 && this.getX() >= 0 && this.getY() > 100)
            {
                state=1;
            }
            else if(this.getY()+Commons.MonsterSize <= 100 && this.getY() >= 0 && this.getX() < 300)
            {
                state=2;
            }
            else if(this.getX()+Commons.MonsterSize <= 400 && this.getX() >= 300 && this.getY() < 300)
            {
                state=3;
            }
            else if(this.getY()+Commons.MonsterSize <= 400 && this.getY() >= 300 && this.getX() > 100)
            {
                state=4;
            }
            if(getX()==0 && getY()<=100) state=2;
            if(getY() == 0 && getX()>=300) state=2;
            if(getX()+Commons.MonsterSize == 400 && getY()>300) state=3;
            if(getX() == 0 && getY()<100) state=1;
            if(this.x+Commons.MonsterSize == 100 && state == 1)
            {
                leftRight=true;
            }
            else if(this.getX() == 0 && state == 1)
            {
                leftRight=false;
            }
            else if(this.y == 0 && state == 2)
            {
                upBottom=false;
            }
            else if(this.y+Commons.MonsterSize == 100 && state == 2)
            {
                upBottom=true;
            }
            else if(this.getX()+Commons.MonsterSize == 400 && state == 3)
            {
                leftRight=true;
            }
            else if(this.x == 300 && state == 3)
            {
                leftRight=false;
            }
            else if(this.y == 300 && state == 4)
            {
                upBottom=false;
            }
            else if(this.y+Commons.MonsterSize == 400 && state == 4)
            {
                upBottom=true;
            }
            if(leftRight == true) this.setX((this.getX())-1);
            else this.setX((this.getX())+1);
            if(upBottom == true) this.setY((this.getY())-1);
            else this.setY((this.getY())+1);
        }

    }

    /**
     *
     * @param type
     */
    public void setHit(int type)
    {
        this.hit = type;
    }

    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        Integer health = this.getHealth();
        if(health > 0)
        {
            if(this.getX() == 100 && this.getY()>300 && this.getY()+Commons.MonsterSize<400)
            {
                Game.getInstance().getMonsters().remove(this);
                Game.getInstance().monsterCount--;
                String lives = Display.getInstance().getInfoPanel().getCurrentLivesLabel().getText();
                Integer livesInt = Integer.parseInt(lives);
                livesInt--;
                Game.getInstance().live--;
                lives = livesInt.toString();
                Display.getInstance().getInfoPanel().getCurrentLivesLabel().setText(lives);
                if(Game.getInstance().live == 0) System.exit(0);

            }
            else {
                g.setColor(Color.ORANGE);
                g.fillRect(this.getX(), this.getY(), Commons.MonsterSize, Commons.MonsterSize);
                if(hit==0){
                    g.setColor(Color.WHITE);
                    if(health <= 9)
                        g.drawChars(health.toString().toCharArray(), 0, 1, this.getX()+Commons.MonsterSize/4, this.getY()+Commons.MonsterSize/2);
                    else if(health < 100)
                        g.drawChars(health.toString().toCharArray(), 0, 2, this.getX()+Commons.MonsterSize/4, this.getY()+Commons.MonsterSize/2);
                    else
                        g.drawChars(health.toString().toCharArray(), 0, 3, this.getX()+Commons.MonsterSize/4, this.getY()+Commons.MonsterSize/2);

                }
                if(hit==1)
                {
                    g.setColor(Color.RED);
                    if(health <= 9)
                        g.drawChars(health.toString().toCharArray(), 0, 1, this.getX()+Commons.MonsterSize/4, this.getY()+Commons.MonsterSize/2);
                    else if(health < 100)
                        g.drawChars(health.toString().toCharArray(), 0, 2, this.getX()+Commons.MonsterSize/4, this.getY()+Commons.MonsterSize/2);
                    else
                        g.drawChars(health.toString().toCharArray(), 0, 3, this.getX()+Commons.MonsterSize/4, this.getY()+Commons.MonsterSize/2);
                    hit=0;
                }
                if(hit==2)
                {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setStroke(new BasicStroke(10));
                    g2.setColor(Color.BLUE);
                    g2.drawRect(this.getX(), this.getY(), Commons.MonsterSize, Commons.MonsterSize);
                    g2.setStroke(new BasicStroke(1));
                    g.setColor(Color.RED);
                    if(health <= 9)
                        g.drawChars(health.toString().toCharArray(), 0, 1, this.getX()+Commons.MonsterSize/4, this.getY()+Commons.MonsterSize/2);
                    else if(health < 100)
                        g.drawChars(health.toString().toCharArray(), 0, 2, this.getX()+Commons.MonsterSize/4, this.getY()+Commons.MonsterSize/2);
                    else
                        g.drawChars(health.toString().toCharArray(), 0, 3, this.getX()+Commons.MonsterSize/4, this.getY()+Commons.MonsterSize/2);
                    hit=0;
                }
                if(hit==3)
                {

                    Graphics2D g2 = (Graphics2D) g;
                    g2.setStroke(new BasicStroke(10));
                    g2.setColor(Color.GREEN);
                    g2.drawRect(this.getX(), this.getY(), Commons.MonsterSize, Commons.MonsterSize);
                    g2.setStroke(new BasicStroke(1));
                    g.setColor(Color.RED);
                    if(health <= 9)
                        g.drawChars(health.toString().toCharArray(), 0, 1, this.getX()+Commons.MonsterSize/4, this.getY()+Commons.MonsterSize/2);
                    else if(health < 100)
                        g.drawChars(health.toString().toCharArray(), 0, 2, this.getX()+Commons.MonsterSize/4, this.getY()+Commons.MonsterSize/2);
                    else
                        g.drawChars(health.toString().toCharArray(), 0, 3, this.getX()+Commons.MonsterSize/4, this.getY()+Commons.MonsterSize/2);
                    hit=0;
                }

            }
        }
        else
        {
            Game.getInstance().getMonsters().remove(this);
            Game.getInstance().monsterCount--;
        }
    }
}
