export type Intervention = {
    id?: number;
    date: Date;
    duree: number;
    salaries: { id: number }[];
    materiels: { id: number }[];
    projet: { id: number };
}
