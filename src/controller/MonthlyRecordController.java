package controller;

import model.Observer;
import model.Record;
import model.Subject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MonthlyRecordController implements Subject {
    private DataPresistenceController dataPresistenceController;
    private RecordController recordController;
    private List<Observer> listObservers;

    public MonthlyRecordController(){
        dataPresistenceController = new DataPresistenceController();
        recordController = new RecordController();
        this.listObservers = new ArrayList<>();
    }

    public boolean addNewRecord(double receitaDiaria, double despesaFatura, double despesa, double IVA){
        Record record = this.recordController.createNewRecord(receitaDiaria, despesaFatura, despesa, IVA, LocalDate.now());


        if(record != null){
            this.notifyObservers(); //notify all Observers
            return true;
        }

        return false;
    }

    public boolean editRecord(double receitaDiaria, double despesaFatura, double despesa, double IVA){
        Record record = this.recordController.editRecord(receitaDiaria, despesaFatura, despesa, IVA, LocalDate.now());

        if(record != null){
            this.notifyObservers(); //notify all Observers
            return true;
        }

        return false;
    }

    public List<Record> importDataFromCurrentMonth(){
        return dataPresistenceController.recordsInfoByMonth(LocalDate.now().getMonth().getValue());
    }

    @Override
    public void registerObserver(Observer o) {
        this.listObservers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int index = this.listObservers.indexOf(o);
        if(index >= 0){
            this.listObservers.remove(index);
        }
    }

    @Override
    public void notifyObservers() {
        for(Observer obs : this.listObservers){
            obs.update();
        }
    }
}
