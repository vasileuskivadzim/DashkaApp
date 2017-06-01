package com.dashkasystems.testapp1.Aquarium;

import android.util.Log;

import com.dashkasystems.testapp1.Color;
import com.dashkasystems.testapp1.RandomHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ru.yandex.speechkit.Vocalizer;

/**
 * Created by pandasystems on 5/1/17.
 */

public class AquariumExercise {
    private Aquarium currentAquarium;
    private Aquarium populatedAquarium;

    private Inhabitant[] inhabitCandidates;
    private int rightInhabitCandidateIndex;


    private int stepNumber = -1;
    private List<Inhabitant> stepSequence = new ArrayList<>();


    public AquariumExercise() {
        populatedAquarium = new Aquarium(3);
        currentAquarium = new Aquarium(3);
        populate();
        nextStep();
    }

    public int getDimension() {
        return 3;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public int getRightInhabitCandidateIndex() {
        return rightInhabitCandidateIndex;
    }

    public Inhabitant[] getInhabitCandidates() {
        return inhabitCandidates;
    }

    public boolean nextStep() {
        stepNumber++;
        boolean isEnd = stepNumber == stepSequence.size();
        if (!isEnd) {
            Inhabitant curInhabitant = stepSequence.get(stepNumber);
            List<Integer> indexes = populatedAquarium.occupiedPlacesByInhabitant(curInhabitant);
            Integer rootIndex = indexes.get(indexes.size()-1);
            currentAquarium.addInhabitant(curInhabitant, rootIndex);

            generateInhabitCandidates();
        }
        return !isEnd;
    }

    public void vocalize() {
        List<Relation> relations = currentAquarium.relationsForInhabitant(stepSequence.get(stepNumber));
        int relationsCount = relations.size();
        int index = RandomHelper.getInt(relationsCount);
        Relation relation = relations.get(index);
        String description = relation.verbalDescription();

        Vocalizer vocalizer = Vocalizer.createVocalizer("ru-RU", description, true);
        vocalizer.start();
    }

    private void generateInhabitCandidates() {
        int candidatesCount = 3;
        rightInhabitCandidateIndex = RandomHelper.getInt(candidatesCount);

        inhabitCandidates = new Inhabitant[candidatesCount];
        Inhabitant rightInhabitant = stepSequence.get(stepNumber);
        List<Color> colors = new ArrayList<>(Arrays.asList(Color.all()));
        if (rightInhabitant.getClass() == Fish.class) {
            Fish fish = (Fish) rightInhabitant;
            int index = 0;
            for (int i=0; i<colors.size(); i++) {
                if (colors.get(i).color == fish.color.color) {
                    index = i;
                }
            }
            colors.remove(index);
        }

        for (int i = 0; i < candidatesCount; i++) {
            if (i == rightInhabitCandidateIndex) {
                inhabitCandidates[i] = rightInhabitant;
            } else {
                int colorIndex = RandomHelper.getInt(colors.size());
                Color color = colors.get(colorIndex);
                inhabitCandidates[i] = new Fish(color);
                colors.remove(colorIndex);
            }
        }

    }

    public List<Integer> targetIndexesForCurrentStep() {
        Inhabitant inhabitant = stepSequence.get(stepNumber);
        return populatedAquarium.occupiedPlacesByInhabitant(inhabitant);
    }


    private void populate() {
        List<Integer> freePlantPlaces = populatedAquarium.freePlacesForSpot(Inhabitant.Spot.BOTTOM_DOUBLE);
        int plantRootIndex = RandomHelper.getInt(freePlantPlaces.size());
        Plant bigPlant = new Plant(Inhabitant.Spot.BOTTOM_DOUBLE);
        populatedAquarium.addInhabitant(bigPlant, freePlantPlaces.get(plantRootIndex));

        stepSequence.add(bigPlant);

        List<Integer> freeSmallPlantPlaces = populatedAquarium.freePlacesForSpot(Inhabitant.Spot.BOTTOM);
        int smallPlantRootIndex = RandomHelper.getInt(freeSmallPlantPlaces.size());
        Plant smallPlant = new Plant(Inhabitant.Spot.BOTTOM);
        populatedAquarium.addInhabitant(smallPlant, freeSmallPlantPlaces.get(smallPlantRootIndex));

        stepSequence.add(smallPlant);

        List<Integer> freeFishPlaces = populatedAquarium.freePlacesForSpot(Inhabitant.Spot.ANY);
        List<Color> colors = new ArrayList<>(Arrays.asList(Color.all()));
        int fishCount = 4;
        for (int i = 0; i < fishCount; i++) {
            int colorIndex = RandomHelper.getInt(colors.size());
            Color color = colors.get(colorIndex);
            Fish fish = new Fish(color);

            int freeIndex = RandomHelper.getInt(freeFishPlaces.size());
            populatedAquarium.addInhabitant(fish, freeFishPlaces.get(freeIndex));

            colors.remove(colorIndex);
            freeFishPlaces.remove(freeIndex);

            stepSequence.add(fish);
        }
    }


}
