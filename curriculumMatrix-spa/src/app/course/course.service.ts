import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Course } from './course';
import { MatrixItem } from './matrix/matrix-item.interface';

@Injectable({ providedIn: 'root' })
export class CourseService {
    
    constructor(
        private http: HttpClient
    ) {}

    getCourse(id: string) {
        return this.http
            .get<Course>('http://localhost:8080/api/course/'+ id);
    }

    getCourseMatrix(year: string, courseId: string) {
        return this.http
            .get<MatrixItem[]>('http://localhost:8080/api/course/matrix/'+ year + '/' + courseId);
    }

    updateCourse(id: string, name: string) {
        return this.http
            .put<Course>('http://localhost:8080/api/course/99' , { "name": "Teste" });
    }
}