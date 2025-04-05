package com.example.demo.domain.models.value.objects;

import com.example.demo.domain.exceptions.InvalidCardNumberException;

import java.util.Objects;

import static com.example.demo.domain.models.value.objects.utils.ConstantsValueObjects.CARD_NUMBER_VALUE_CANNOT_BE_NULL_MESSAGE;
import static com.example.demo.domain.models.value.objects.utils.ConstantsValueObjects.REGEX_REMOVE_SPACES_AND_DASHESH;

public final class CardNumber {
    private final String value;

    public CardNumber(String value) {
        Objects.requireNonNull(value, CARD_NUMBER_VALUE_CANNOT_BE_NULL_MESSAGE);
        String cleanValue = value.replaceAll(REGEX_REMOVE_SPACES_AND_DASHESH, "");
        if (!isValidCardNumber(cleanValue)) {
            throw new InvalidCardNumberException();
        }
        this.value = cleanValue;
    }

    public String getValue() {
        return value;
    }

    public String getMaskedValue() {
        if (value.length() <= 4) {
            return "****";
        }
        return "************" + value.substring(value.length() - 4);
    }

    /**
     * @isValidCarNumber() is Luhn Algorithm
     */
    private boolean isValidCardNumber(String cardNumber) {
        if (cardNumber == null || !cardNumber.matches("\\d+")) {
            return false;
        }

        int[] digits = new int[cardNumber.length()];
        for (int i = 0; i < cardNumber.length(); i++) {
            digits[i] = Character.getNumericValue(cardNumber.charAt(i));
        }

        int sum = 0;
        boolean alternate = false;
        for (int i = digits.length - 1; i >= 0; i--) {
            int digit = digits[i];
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardNumber that = (CardNumber) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
