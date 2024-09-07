package com.toyproject.hyeonworld.api.submission.domain.dto.in;/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 7.
*/
public record SubmissionCommand (
        long partyId,
        long userId,
        String text,
        long number
    )
    {
}
