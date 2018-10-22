package by.project.requestprocessingsystem.requestprocessingsystem.dao;

import by.project.requestprocessingsystem.requestprocessingsystem.domain.Request;
import by.project.requestprocessingsystem.requestprocessingsystem.spring.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RequestService implements RequestDao {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Request save(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public void updateStatus(String text, long id) {
        requestRepository.updateStatus(text, id);
    }


    @Override
    public Page<Request> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return requestRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }
}
