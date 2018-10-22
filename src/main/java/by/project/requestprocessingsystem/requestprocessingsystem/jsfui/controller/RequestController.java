package by.project.requestprocessingsystem.requestprocessingsystem.jsfui.controller;

import by.project.requestprocessingsystem.requestprocessingsystem.dao.RequestDao;
import by.project.requestprocessingsystem.requestprocessingsystem.domain.Request;
import by.project.requestprocessingsystem.requestprocessingsystem.jsfui.model.LazyDataTable;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

@ManagedBean
@SessionScoped
@Component
@Getter
@Setter
public class RequestController extends AbstractController<Request> {

    private int rowsCount = 20;

    private int first;

    @Autowired
    private RequestDao requestDao;
    
    private Request selectedRequest;

    private LazyDataTable<Request> lazyModel;

    private Page<Request> requestPages;

    @PostConstruct
    public void init() {
        lazyModel = new LazyDataTable(this);
    }

    public void save() {
        requestDao.save(selectedRequest);
        RequestContext.getCurrentInstance().execute("PF('dialogRequest').hide()");
    }


    @Override
    public Page<Request> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        if (sortField == null) {
            sortField = "name";
        }
        requestPages = requestDao.getAll(pageNumber, pageSize, sortField, sortDirection);
        return requestPages;
    }

    public void addAction() {
        selectedRequest = new Request();
        showEditDialog();
        RequestContext.getCurrentInstance().update("tabView:dialogRequest");
    }

    public void setStatusDenied(long id){
        requestDao.updateStatus("Отказано", id);
    }

    public void setStatusComplited(long id){
        requestDao.updateStatus("Выполнено", id);
    }

    private void showEditDialog() {
        RequestContext.getCurrentInstance().execute("PF('dialogRequest').show()");
    }
}
