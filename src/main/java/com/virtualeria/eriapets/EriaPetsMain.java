package com.virtualeria.eriapets;

import com.virtualeria.eriapets.entities.BasePetEntity;
import com.virtualeria.eriapets.entities.EntityRegistryPets;
import com.virtualeria.eriapets.entities.OthoPetEntity;
import com.virtualeria.eriapets.events.EventsRegistry;
import com.virtualeria.eriapets.events.OthoShellBreakCallback;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import software.bernie.geckolib3.GeckoLib;

import java.util.List;

public class EriaPetsMain implements ModInitializer {

    public static String ModID = "eriapets";


    @Override
    public void onInitialize() {

        System.out.println("[EriaPets] Initialize");
        GeckoLib.initialize();
        EntityRegistryPets.initialize();
        EventsRegistry.registerEvents();
    }



}
