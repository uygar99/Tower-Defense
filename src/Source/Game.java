package Source;

import jdk.jfr.Unsigned;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Game {

    private static final Game _inst = new Game();

    public static Game getInstance() {
        return _inst;
    }
    //TODO
    public static int stepCounter;
    public static int gold;
    public static int live;
    public static int waves;
    public static int kills;
    public Integer monsterCount;
    public static Integer xi;
    public static Integer yi;
    private ArrayList<Monster> monsters = new ArrayList<Monster>();
    public ArrayList<Tower> towers = new ArrayList<Tower>();
    public IMonsterStrategy strategy;
    public boolean isZigzag;
    public boolean strategyType = false;

    /**
     *
     */
    public Game() {
        //TODO
        this.gold = 25;
        this.live = 3;
        this.waves = 0;
        monsterCount = this.waves;
        stepCounter = 0;
        kills=0;
        Random random = new Random();
        int a = random.nextInt(2);
        if(a==1) isZigzag=true;
        else isZigzag=false;
    }
    public static void setGold(int gold) {
        Game.gold = gold;
    }

    /**
     *
     */
    public void setStrategy() {
        if(isZigzag) strategy= new MonsterZigZagStrategy();
        else strategy = new MonsterCircularStrategy();
    }

    /**
     *
     * @param vector2D
     * @param a
     */
    public void coordinates(Vector2D vector2D, char a){
        if(a=='r') {
            boolean flag=false;
            Tower tower = new TowerRegularFactory().createTower(vector2D);
            tower.setX(vector2D.getIntX());
            tower.setY(vector2D.getIntY());
            tower.normalize();
            for(int i=0;i<towers.size();i++)
            {
                if(towers.get(i).x == tower.x && towers.get(i).y == tower.y)  flag=true;
            }
            if(!flag && tower.check()) towers.add(tower);
        }
        else if(a=='i') {
            boolean flag=false;
            Tower tower = new TowerIceFactory().createTower(vector2D);
            tower.setX(vector2D.getIntX());
            tower.setY(vector2D.getIntY());
            tower.normalize();
            for(int i=0;i<towers.size();i++)
            {
                if(towers.get(i).x == tower.x && towers.get(i).y == tower.y)  flag=true;
            }
            if(!flag && tower.check()) towers.add(tower);
        }
        else if(a=='p') {
            boolean flag=false;
            Tower tower = new TowerPoisonFactory().createTower(vector2D);
            tower.setX(vector2D.getIntX());
            tower.setY(vector2D.getIntY());
            tower.normalize();
            for(int i=0;i<towers.size();i++)
            {
                if(towers.get(i).x == tower.x && towers.get(i).y == tower.y)  flag=true;
            }
            if(!flag && tower.check()) towers.add(tower);
        }
    }

    /**
     *
     * @param g
     */
    public void paint(Graphics g) {
        //TODO
        for (int i=0;i<Game.getInstance().monsters.size();i++) {
            monsters.get(i).paint(g);
        }
        for(int i = 0 ; i <towers.size() ; i++){
            if(towers.get(i).towerType == 2)
            {
                towers.get(i).paint(g);
            }
            if(towers.get(i).towerType == 1)
            {
                towers.get(i).paint(g);

            }
            if(towers.get(i).towerType == 3)
            {
                towers.get(i).paint(g);
            }

        }
    }

    /**
     *
     */
    public void step() {
        //TODO
        if(monsters.isEmpty())
        {
            String wave = Display.getInstance().getInfoPanel().getCurrentWaveLabel().getText();
            Integer waveInt = Integer.parseInt(wave);
            waveInt++;
            wave = waveInt.toString();
            Display.getInstance().getInfoPanel().getCurrentWaveLabel().setText(wave);
            waves++;
            monsterCount=waves;
            for(int i = 0; i<monsterCount; i++)
            {

                Random x = new Random();
                xi = x.nextInt(Commons.StartWidth) + Commons.StartX;
                Random y = new Random();
                yi = y.nextInt(Commons.StartHeight) + Commons.StartY;
                Monster monster = new Monster();
                monster.setX(xi);
                monster.setY(yi);
                monster.setiMonsterStrategy(strategy);
                monsters.add(monster);

            }
        }
        for(int i = 0; i<Game.getInstance().monsters.size(); i++)
        {
            if(!strategyType)
            {
                monsters.get(i).step();
            }
//            else
//            {
//                monsters.get(i).iMonsterStrategy = new MonsterZigZagStrategy();
//            }
        }

        for(int i=0;i<towers.size();i++)
        {
            towers.get(i).step();
        }
        stepCounter++;
        String gold = Display.getInstance().getInfoPanel().getCurrentGoldLabel().getText();
        Integer goldInt = Integer.parseInt(gold);
        String kills = Display.getInstance().getInfoPanel().getCurrentKillsLabel().getText();
        Integer killsInt = Integer.parseInt(kills);
        Display.getInstance().getGamePanel().repaint();
        gold = goldInt.toString();
        kills = killsInt.toString();
        Display.getInstance().getInfoPanel().getCurrentGoldLabel().setText(gold);
        Display.getInstance().getInfoPanel().getCurrentKillsLabel().setText(kills);
        Display.getInstance().setVisible(true);
    }

    /**
     *
     * @return Integer
     */
    public Integer getMonsterCount() {
        return monsterCount;
    }

    /**
     *
     * @return ArrayList<Monster>
     */
    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    /**
     *
     */
    //You can make changes
    public static void startGame() {
        Display.getInstance().setVisible(true);
        //Optional additions
        new Timer(5, actionEvent -> {
            Game.getInstance().step();
            //Optional additions
            
        }).start();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::startGame);
    }
}
