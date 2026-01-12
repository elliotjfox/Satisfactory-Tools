package com.example.satisfactorytools.factorycalculator.network;

public record NetworkSettings(boolean useExactMultipliers) {

    public static final NetworkSettings DEFAULT_SETTINGS = new NetworkSettingsBuilder().build();

    public static class NetworkSettingsBuilder {

        private boolean useExactMultipliers;

        public NetworkSettingsBuilder() {
            useExactMultipliers = true;
        }

        public NetworkSettingsBuilder useExactMultipliers(boolean mergeMachines) {
            this.useExactMultipliers = mergeMachines;
            return this;
        }

        public NetworkSettings build() {
            return new NetworkSettings(
                    useExactMultipliers
            );
        }
    }
}
