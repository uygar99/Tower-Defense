package Source;

public class TowerRegularFactory implements ITowerFactory {

    //TODO
    @Override
    public Tower createTower(Vector2D position) {
        Tower towerRegular = new TowerRegular();
        return towerRegular;
    }
}
