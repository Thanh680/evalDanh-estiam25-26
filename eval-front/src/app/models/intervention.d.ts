export type Intervention = {
    id?: number;
    date: Date;
    duree: number;
    salarie: Salarie[];
    projet: { id: number };
}
