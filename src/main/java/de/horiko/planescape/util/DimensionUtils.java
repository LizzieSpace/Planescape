package de.horiko.planescape.util;

import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for dimension-related operations.
 * This class follows the project's code style and demonstrates best practices.
 */
public class DimensionUtils {
    private static final Logger LOGGER = LogManager.getLogger("Planescape/DimensionUtils");

    /**
     * Creates a dimension identifier from a string.
     * 
     * @param dimensionName The name of the dimension
     * @return A ResourceLocation representing the dimension
     */
    public static ResourceLocation createDimensionId(String dimensionName) {
        if (dimensionName == null || dimensionName.isEmpty()) {
            LOGGER.warn("Attempted to create dimension ID with null or empty name");
            throw new IllegalArgumentException("Dimension name cannot be null or empty");
        }

        // Ensure the dimension name is valid
        dimensionName = dimensionName.toLowerCase().replaceAll("[^a-z0-9_.-]", "_");

        LOGGER.debug("Created dimension ID: {}", dimensionName);
        return ResourceLocation.fromNamespaceAndPath("planescape", dimensionName);
    }

    /**
     * Validates if a dimension name is valid according to Minecraft's naming rules.
     * 
     * @param dimensionName The name to validate
     * @return true if the name is valid, false otherwise
     */
    public static boolean isValidDimensionName(String dimensionName) {
        if (dimensionName == null || dimensionName.isEmpty()) {
            return false;
        }

        // Check if the name contains only valid characters
        return dimensionName.matches("^[a-z0-9_.-]+$");
    }
}
