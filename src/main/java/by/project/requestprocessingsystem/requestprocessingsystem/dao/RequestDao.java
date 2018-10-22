package by.project.requestprocessingsystem.requestprocessingsystem.dao;

import by.project.requestprocessingsystem.requestprocessingsystem.domain.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface RequestDao {

    Request save (Request request);

    void updateStatus (String text, long id);

    Page<Request> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection);
}
