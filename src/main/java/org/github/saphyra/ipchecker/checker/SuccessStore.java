package org.github.saphyra.ipchecker.checker;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
class SuccessStore {
    private final Map<String, Boolean> successMap = new HashMap<>();

    boolean hasFailed() {
        return successMap.values().stream()
            .anyMatch(isSuccess -> !isSuccess);
    }

    void invalidate() {
        successMap.entrySet().forEach(entry -> entry.setValue(false));
    }

    boolean needToSendMail(String addressee) {
        return !hasMailSuccessfullySent(addressee);
    }

    private Boolean hasMailSuccessfullySent(String addressee) {
        return Optional.ofNullable(successMap.get(addressee)).orElse(false);
    }

    void updateSentStatus(String addressee, boolean success) {
        successMap.put(addressee, success);
    }
}
