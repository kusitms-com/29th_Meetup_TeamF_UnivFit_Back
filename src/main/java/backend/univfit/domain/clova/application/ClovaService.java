package backend.univfit.domain.clova.application;

import backend.univfit.domain.clova.api.dto.request.FoundationSummaryRequest;
import backend.univfit.domain.clova.api.dto.request.SummaryOption;
import backend.univfit.domain.clova.api.dto.response.SummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClovaService {
    private final RestTemplate restTemplate;

    @Value("${naver.client-id}")
    private String CLIENT_ID;
    @Value("${naver.client-secret}")
    private static String CLIENT_SECRET;
    @Value("${naver.api-url}")
    private static String API_URL;

    public String summarizeText(FoundationSummaryRequest request) {
        SummaryOption.Document document = FoundationSummaryRequest.toDocument(request.title(), request.content());
        SummaryOption.Option option = new SummaryOption.Option("ko", "general", 3, 5);
        SummaryOption summaryOption = new SummaryOption(document, option);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-NCP-APIGW-API-KEY-ID", CLIENT_ID);
        headers.set("X-NCP-APIGW-API-KEY", CLIENT_SECRET);

        HttpEntity<SummaryOption> requestEntity = new HttpEntity<>(summaryOption, headers);

        return restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, SummaryResponse.class)
                .getBody()
                .getSummary();
    }
}