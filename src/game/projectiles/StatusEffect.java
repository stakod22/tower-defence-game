package game.projectiles;

public class StatusEffect {
    public DamageType damageType;
    public int damageDuration;

    public StatusEffect(DamageType damageType, int damageDuration) {
        this.damageType = damageType;
        this.damageDuration = damageDuration;
    }
}
