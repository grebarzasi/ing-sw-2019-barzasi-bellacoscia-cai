package it.polimi.ingsw.cards.weapon.weapon_builder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * class that create a single weapon given the name. It parse a json file containing weapon info.
 * Uses {@link EffectBuilder} to parse and create the chain of effect.
 * @author Gregorio Barzasi
 */
public class WeaponBuilder {

    public static Weapon buildWeapon(String name) {
        ObjectMapper mapper = new ObjectMapper();
        // path of weapons data
        String path = "src\\main\\java\\it\\polimi\\ingsw\\cards\\weapon\\weaponsData\\";
        File jsonFile = new File(String.format("%s%s.json", path, name));
        try {

            //open json file and start parsing
            JsonNode rootNode = mapper.readTree(jsonFile);

            //parse the chamber value
            JsonNode chamberNode = rootNode.path("chamber");
            Ammo chamber = EffectBuilder.buildCost(chamberNode);

            //create a new weapon with name and chamber
            Weapon weaponBuilt = new Weapon(rootNode.path("name").textValue(), chamber);

            //create effects iterating on effects name. set correct place for each effect
            Iterator<String> effectsIterator = rootNode.path("effects").fieldNames();
            while (effectsIterator.hasNext()) {
                String effName = effectsIterator.next();
                //uses effect builder
                Effect effBuilt = EffectBuilder.buildEffect(rootNode.path("effects").path(effName));
                //set myWeapon
                effBuilt.setMyWeapon(weaponBuilt);

                switch (effName) {
                    case "basic":
                        weaponBuilt.setBasicEffect(effBuilt);
                        break;
                    case "addOne":
                        weaponBuilt.setAddOneEffect(effBuilt);
                        break;
                    case "addTwo":
                        weaponBuilt.setAddTwoEffect(effBuilt);
                        break;
                    case "alternative":
                        weaponBuilt.setAlternativeEffect(effBuilt);
                        break;
                    case "extraMove":
                        weaponBuilt.setExtraMove(effBuilt);
                        break;
                }
            }
            return weaponBuilt;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
