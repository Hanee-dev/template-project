package com.tp.template.infrastructure.develop;

import com.tp.template.infrastructure.enums.LanguageId;
import com.tp.template.infrastructure.i18n.translation.adapter.out.persistence.LanguageTranslationAdapter;
import com.tp.template.infrastructure.i18n.translation.application.domain.LanguageTranslation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("!prod")
public class DataInitializer implements ApplicationRunner {

    private final LanguageTranslationAdapter languageTranslationAdapter;

    @Override
    public void run(ApplicationArguments args) {
        List<LanguageTranslation> translations = languageTranslationAdapter.all();

        if(!translations.isEmpty()) {
            return;
        }

        for(LanguageId languageId : LanguageId.values()) {
            LanguageTranslation languageTranslation = LanguageTranslation.builder()
                    .languageId(languageId)
                    .ko("테스트 번역문 (" + languageId.name() + ")")
                    .en("Test Translation (" + languageId.name() + ")")
                    .zh("测试翻译 (" + languageId.name() + ")")
                    .build();

            languageTranslationAdapter.save(languageTranslation);
        }
    }
}
