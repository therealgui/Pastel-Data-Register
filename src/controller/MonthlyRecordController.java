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
    private List<Record> listRecords;

    public MonthlyRecordController(){
        this.dataPresistenceController = new DataPresistenceController();
        this.recordController = new RecordController();
        this.listObservers = new ArrayList<>();
        this.listRecords = this.importDataFromCurrentMonth();
    }

    /**
     * Add a new record
     *
     * @param receitaDiaria
     * @param despesaFatura
     * @param despesa
     * @param IVA
     * @return boolean
     */
    public boolean addNewRecord(double receitaDiaria, double despesaFatura, double despesa, double IVA){
        Record record = this.recordController.createNewRecord(receitaDiaria, despesaFatura, despesa, IVA, LocalDate.now());

        if(record != null){
            this.listRecords = this.importDataFromCurrentMonth();
            this.notifyObservers(); //notify all Observers
            return true;
        }

        return false;
    }

    /**
     * Edit current record
     *
     * @param receitaDiaria
     * @param despesaFatura
     * @param despesa
     * @param IVA
     * @return boolean
     */
    public boolean editRecord(double receitaDiaria, double despesaFatura, double despesa, double IVA){
        Record record = this.recordController.editRecord(receitaDiaria, despesaFatura, despesa, IVA, LocalDate.now());

        if(record != null){
            this.listRecords = this.importDataFromCurrentMonth();
            this.notifyObservers(); //notify all Observers
            return true;
        }

        return false;
    }

    /**
     * Check if record already exists
     *
     * @return boolean
     */
    public boolean checkIfRecordExists(){

        if(this.listRecords.isEmpty()){
            return false;
        }

        return this.recordController.validateRecord(this.listRecords.get(this.listRecords.size()-1));
    }

    /**
     * Retrieve data from current month
     *
     * @return List of Record Objects
     */
    public List<Record> importDataFromCurrentMonth(){
        return dataPresistenceController.recordsInfoByMonth(LocalDate.now().getMonth().getValue());
    }

    /**
     * Save changes
     *
     * @return boolean
     */
    public boolean saveRecord(){
        return this.recordController.save();
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
