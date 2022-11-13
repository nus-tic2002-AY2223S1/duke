package domain.dto;

import java.time.LocalDate;

public class PostponeDeadlineDto {
    private int index;
    private LocalDate postponeToDate;

    public PostponeDeadlineDto(int index, LocalDate postponeToDate) {
        this.index = index;
        this.postponeToDate = postponeToDate;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public LocalDate getPostponeToDate() {
        return postponeToDate;
    }

    public void setPostponeToDate(LocalDate postponeToDate) {
        this.postponeToDate = postponeToDate;
    }
}
