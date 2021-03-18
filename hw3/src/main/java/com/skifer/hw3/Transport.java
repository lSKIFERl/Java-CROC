package com.skifer.hw3;

/**
 * Описывает основную модель поведения и состояния для любого транспорта компании, а также его стоимость и номер
 */
public abstract class Transport {

    /**
     * Показывает физическое состояние транспорта
     */
    private State state;

    /**
     * Показывает статус аренды транспорта
     */
    private Rent rent;

    /**
     * Идентификатор транспорта
     */
    private int id;

    /**
     * Цена на аренду транспорта
     */
    private int rentPrice;

    /**
     * Конструктор с типичными для всего арендуемого транспорта параметрами
     * @param state физическое состояние
     * @param rent статус аренды
     * @param id идентификатор
     * @param rentPrice цена за аренду
     */
    public Transport(State state, Rent rent, int id, int rentPrice) {
        this.state = state;
        this.rent = rent;
        this.id = id;
        this.rentPrice = rentPrice;
    }

    /**
     * Если необходим ремонт, то вызывается этот метод и устанавливается статус "Недоступен"
     * Также транспорт отправляется в соответствующий своим повреждениям сервис
     * После его возвращения он сразу становится доступен,
     * даже если имеет незначительные повреждения (царапины, вмятины)
     * или не в идеальном состоянии (грязный, облезла краска)
     */
    public void repair() {
        if (getState().ordinal() > 2) {
            setRent(Rent.NOT_AVAILABLE);
            service(getState());
        } else {
            cosmeticService(getState());
        }
        setRent(Rent.FREE);
    }

    /**
     * Сервис косметического ремонта, также справится с лёгкими повреждениями
     * @param state состояние, нужно чтобы определить, справится ли сервис с задачей
     */
    public void cosmeticService(State state) {
        if(state.ordinal() < 3)
            setState(State.PERFECT);
    }

    /**
     * Сервис полного ремонта, не имеет специалистов по косметическому ремонту
     * @param state состояние, нужно для опреления подходит ли задача сервису
     */
    public void service(State state){
        if(state.ordinal() > 2)
            setState(State.ACCEPTABLE);
    }

    /**
     * Вернёт цену на аренду
     * @return цена
     */
    public int getRentPrice() {
        return rentPrice;
    }

    /**
     * Установит цену на аренду
     * @param rentPrice цена
     */
    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    /**
     * Вернёт текущее физическое состояние транспорта
     * @return Состояние
     */
    public State getState() {
        return state;
    }

    /**
     * Установит текущее физическое состояние транспорта
     * @param state состояние
     */
    public void setState(State state) {
        this.state = state;
        if(state.ordinal() > 3)
            setRent(Rent.NOT_AVAILABLE);
        setRentPrice(getRentPrice() - (getRentPrice() / (state.ordinal() + 5))* state.ordinal());
    }

    /**
     * Вернёт статус арендованности транспорта
     * @return статус
     */
    public Rent getRent() {
        return rent;
    }

    /**
     * Установит текущий статус арендованности транспорта
     * @param rent статус
     */
    public void setRent(Rent rent) {
        this.rent = rent;
    }
}
