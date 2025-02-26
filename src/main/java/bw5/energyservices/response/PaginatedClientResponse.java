package bw5.energyservices.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginatedClientResponse<T> {

    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int currentPage;
    private int size;
}