package prove02;

import java.awt.*;
import java.util.Random;

public class Wolf extends Creature implements Movable, Aggressor, Aware {
    Random _rand;
    private Direction direction;
    private Boolean canSpawn;

    /**
     * Creates an Wolf with 1 health point.
     */
    public Wolf() {
        _rand = new Random();
        _health = 1;
        canSpawn = false;
    }

    // No javadocs are necessary for these methods because they will inherit the
    // documentation from the superclass. We only need to add docs here if we are
    // doing something non-obvious in our overridden version.

    public Boolean isAlive() {
        return _health > 0;
    }

    public Shape getShape() {
        return Shape.Square;
    }

    public Color getColor() {
        return new Color(150, 150, 150);
    }

    /**
     * If the creature we've encountered is a plant, we'll eat it. Otherwise, we ignore it.
     * @param target The {@link Creature} we've encountered.
     */
    public void attack(Creature target) {
        // Animals only eat plants. Give the plant 1 damage
        // and increase our health by one.
        if(target instanceof Hunter) {
            target.takeDamage(1);
            _health++;
        } else if (target instanceof Animal) {
            target.takeDamage(2);
            _health += 2;
        }
        canSpawn = true;
    }

    /**
     * Move the wolf in a random direction.
     */
    public void move() {

        // Choose a random direction each time move() is called.
        switch(_rand.nextInt(4)) {
            case 0:
                _location.x++;
                break;
            case 1:
                _location.x--;
                break;
            case 2:
                _location.y++;
                break;
            case 3:
                _location.y--;
                break;
            default:
                break;
        }
    }

    @Override
    public void senseNeighbors(Creature above, Creature below, Creature left, Creature right) {
        if (direction == Direction.Up) {
            if (above instanceof Animal) {
                direction = Direction.Up;
            }
            else if (right instanceof Animal) {
                direction = Direction.Right;
            }
            else if (below instanceof Animal) {
                direction = Direction.Down;
            }
            direction = Direction.Left;
        }
        else if (direction == Direction.Right) {
            if (right instanceof Animal) {
                direction = Direction.Right;
            }
            else if (below instanceof Animal) {
                direction = Direction.Down;
            }
            else if (left instanceof Animal) {
                direction = Direction.Left;
            }
            else if (above instanceof Animal) {
                direction = Direction.Up;
            }
        }
        else if (direction == Direction.Down) {
            if (below instanceof Animal) {
                direction = Direction.Down;
            }
            else if (left instanceof Animal) {
                direction = Direction.Left;
            }
            else if (above instanceof Animal) {
                direction = Direction.Up;
            }
            else if (right instanceof Animal) {
                direction = Direction.Right;
            }
        }
        else if (direction == Direction.Left) {
            if (left instanceof Animal) {
                direction = Direction.Left;
            }
            else if (above instanceof Animal) {
                direction = Direction.Up;
            }
            else if (right instanceof Animal) {
                direction = Direction.Right;
            }
            else if (below instanceof Animal) {
                direction = Direction.Down;
            }
        }
    }
    public Creature spawnNewCreature() {
        if (canSpawn) {
            canSpawn = false;
            Wolf spawn = new Wolf();
            spawn.setLocation(new Point(_location.x - 1, _location.y));
            return spawn;
        }
        return null;
    }
}
