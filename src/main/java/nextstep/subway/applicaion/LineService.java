package nextstep.subway.applicaion;

import nextstep.subway.applicaion.dto.LineRequest;
import nextstep.subway.applicaion.dto.LineResponse;
import nextstep.subway.domain.Line;
import nextstep.subway.domain.LineRepository;
import nextstep.subway.domain.Station;
import nextstep.subway.domain.StationRepository;
import nextstep.subway.exception.DataNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LineService {

    private final LineRepository lineRepository;
    private final StationRepository stationRepository;

    public LineService(LineRepository lineRepository, StationRepository stationRepository) {
        this.lineRepository = lineRepository;
        this.stationRepository = stationRepository;
    }

    @Transactional
    public LineResponse saveLine(LineRequest lineRequest) {
        Station upStation = stationRepository
            .findById(lineRequest.getUpStationId())
            .orElseThrow(
                () -> new DataNotFoundException("Station 데이터가 없습니다.")
            );

        Station downStation = stationRepository
            .findById(lineRequest.getDownStationId())
            .orElseThrow(
                () -> new DataNotFoundException("Station 데이터가 없습니다.")
            );

        Line createLine = new Line(
            null, lineRequest.getName(), lineRequest.getColor(),
            lineRequest.getDistance(), Arrays.asList(upStation, downStation)
        );

        Line createdLine = lineRepository.save(createLine);
        return new LineResponse(createdLine.getId(), createdLine.getName(), createdLine.getColor(), createdLine.getStations().getStations());
    }

    @Transactional(readOnly = true)
    public LineResponse findOneLine(Long id) {
        Line findLine = lineRepository.findById(id).orElseThrow(
            () -> new DataNotFoundException("Line 데이터가 없습니다.")
        );
        return new LineResponse(
            findLine.getId(), findLine.getName(), findLine.getColor(), findLine.getStations().getStations()
        );
    }

    @Transactional(readOnly = true)
    public List<LineResponse> findAllLines() {
        return lineRepository.findAll().stream().map(
            line -> new LineResponse(line.getId(), line.getName(), line.getColor(), line.getStations().getStations())
        ).collect(Collectors.toList());
    }

    @Transactional
    public void deleteLine(Long id) {
        lineRepository.deleteById(id);
    }

    @Transactional
    public LineResponse editLine(Long id, LineRequest lineRequest) {
        Line findLine = lineRepository.findById(id).orElseThrow(
            () -> new DataNotFoundException("Line 데이터가 없습니다.")
        );
        findLine.edit(lineRequest);
        return new LineResponse(
            findLine.getId(), findLine.getName(), findLine.getColor(), findLine.getStations().getStations()
        );
    }
}
