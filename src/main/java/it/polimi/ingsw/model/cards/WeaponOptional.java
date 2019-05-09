package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.exception.InvalidTargetException;
import it.polimi.ingsw.exception.NoAmmoException;
import it.polimi.ingsw.exception.NoOwnerException;
import it.polimi.ingsw.exception.NotThisKindOfWeapon;
import it.polimi.ingsw.model.TargetParameter;
import it.polimi.ingsw.model.cards.effects.Effect;

import java.util.ArrayList;

public class WeaponOptional extends Weapon {

    private ArrayList<ArrayList<Effect>> optionalEffect= new ArrayList<ArrayList<Effect>>();

    public WeaponOptional(String color, String name, int costBlue, int costRed, int costYellow, ArrayList<Effect> effect, ArrayList<ArrayList<Effect>> optionalEffect) {
        super(color, name, costBlue, costRed, costYellow, effect);
        this.optionalEffect=optionalEffect;
    }

    @Override
    public void fireOptional(ArrayList<TargetParameter> target, int which) throws InvalidTargetException, NoAmmoException, NoOwnerException {

        for(int i=0;i<optionalEffect.get(which).size();i++){
            optionalEffect.get(which).get(i).apply(target.get(i),this.getPreviousTarget());
        }
        return;
    }

    @Override
    public void fireAlternative(ArrayList<TargetParameter> target) throws NotThisKindOfWeapon {
        throw new NotThisKindOfWeapon();
    }

    @Override
    protected boolean canPay(ArrayList<Integer> payment, int which){
        return this.optionalEffect.get(which).get(0).getCostBlue()==payment.get(2) && this.optionalEffect.get(which).get(0).getCostRed()==payment.get(0) && this.optionalEffect.get(which).get(0).getCostYellow()==payment.get(1);

    }

}
