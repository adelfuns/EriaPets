package com.virtualeria.eriapets.events;

import com.virtualeria.eriapets.access.PlayerEntityDuck;
import com.virtualeria.eriapets.entities.OthoPetEntity;
import com.virtualeria.eriapets.entities.SlimerPetEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EventsRegistry {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void registerEvents() {
        othoShellBreakCallbackEvent();
        slimerFallDamageCallback();

    }

    public static void slimerFallDamageCallback() {

        SlimerFallDamageCallback.EVENT.register((player, fallDistance) -> {
            PlayerEntity playerEntity = player;

            float maxFallDistance = 14;
            float minFallDistance = 2;

            Entity ownedEntity = player.world.getEntityById(((PlayerEntityDuck) player).getOwnedPetID());

            if (ownedEntity instanceof SlimerPetEntity) {
                SlimerPetEntity entity = (SlimerPetEntity) ownedEntity;
                boolean isOwnerOfEntity = entity.isOwner(playerEntity.getUuid());
                boolean isEntityAlive = entity.getCustomDeath() == 0;

                if (isOwnerOfEntity && isEntityAlive && fallDistance < maxFallDistance && fallDistance > minFallDistance) {
                    entity.useAbility();

                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.FAIL;
        });
    }

    public static void othoShellBreakCallbackEvent() {
        OthoShellBreakCallback.EVENT.register((player) -> {
            ServerPlayerEntity playerEntity = (ServerPlayerEntity) player;

            Entity ownedEntity = player.world.getEntityById(((PlayerEntityDuck) player).getOwnedPetID());

            if (ownedEntity instanceof OthoPetEntity) {
                OthoPetEntity othoEntity = (OthoPetEntity) ownedEntity;
                boolean isOwnerOfEntity = othoEntity.isOwner(playerEntity.getUuid());
                boolean isEntityAlive = othoEntity.getCustomDeath() == 0;
                if (isOwnerOfEntity && isEntityAlive && othoEntity.abilityIsCooledDown()) {
                    othoEntity.useAbility();
                    othoEntity.playAbilitySound();
                    return ActionResult.SUCCESS;
                }
            }


            return ActionResult.FAIL;
        });
    }
}