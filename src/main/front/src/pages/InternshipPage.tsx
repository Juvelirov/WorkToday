import Header from "@/components/Header";
import { Button } from "@/components/ui/button";
import { fakeInternshipD } from "@/lib/utils";
import { useState } from "react";

export function InternshipPage() {
  const [applicationSubmitted, setApplicationSubmitted] = useState(false);

  return (
    <div className="px-32 my-8">
      <Header />
      <div className="flex justify-between items-center mb-6">
        <h1 className="font-black text-3xl">{fakeInternshipD[0].title}</h1>
        {applicationSubmitted && (
          <div className="bg-green-200 px-3 py-2 rounded-full">
            <p className="text-green-900 text-sm">Заявка подана</p>
          </div>
        )}
      </div>
      <p className="mb-6">
        От компании{" "}
        <span className="bg-[#F3DFFF] rounded-full p-2 text-sm">
          {fakeInternshipD[0].creator.company}
        </span>
      </p>
      <h2 className="font-bold text-2xl mb-2">Обязанности</h2>
      <p className="mb-6">
        В рамках стажировки вы будете участвовать в разработке новых
        функциональных возможностей нашего продукта. Ваши задачи будут включать
        в себя: анализ требований, проектирование решений, разработку кода,
        тестирование и исправление ошибок.
      </p>
      <h2 className="font-bold text-2xl mb-2">Требования</h2>
      <p className="mb-6">
        Мы ищем мотивированных студентов, желающих получить практический опыт в
        сфере разработки программного обеспечения. От вас требуется: Знание
        языков программирования (например, JavaScript, Python), фреймворков
        (например, React, Node.js). Базовые знания работы с базами данных (SQL,
        NoSQL), алгоритмов и структур данных. Умение работать в команде и
        эффективно решать проблемы.
      </p>
      <h2 className="font-bold text-2xl mb-2">Задание</h2>
      <p className="mb-6">
        В качестве тестового задания мы предлагаем вам разработать небольшое
        приложение для управления задачами (to-do list). Оно должно позволять
        пользователям добавлять, удалять и помечать задачи как выполненные.
        Использование React для интерфейса и Node.js для серверной части будет
        преимуществом.
      </p>
      <h2 className="font-bold text-2xl mb-2">Необходимые навыки</h2>
      <p className="mb-6">
        Для успешного выполнения задач стажировки вам понадобятся следующие
        навыки: разработка веб-интерфейсов с использованием HTML, CSS и
        JavaScript; знание фреймворков (React, Vue или Angular); работа с REST
        API и базами данных (SQL и NoSQL); а также soft skills, такие как умение
        эффективно общаться, ответственность и умение работать в команде.
      </p>
      <div className="flex justify-center items-center">
        {!applicationSubmitted && (
          <Button
            className="bg-[#8300E7]"
            onClick={() => setApplicationSubmitted(true)}
          >
            Подать заявку
          </Button>
        )}
      </div>
    </div>
  );
}
