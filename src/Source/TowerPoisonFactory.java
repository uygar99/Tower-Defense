package Source;

public class TowerPoisonFactory implements ITowerFactory {

    //TODO
    @Override
    public Tower createTower(Vector2D position) {
        Tower towerPoison = new TowerPoison();
        return towerPoison;
    }
}
