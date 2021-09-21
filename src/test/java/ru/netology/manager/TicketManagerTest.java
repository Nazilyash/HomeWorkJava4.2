package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Ticket;
import ru.netology.domain.TicketByTravelTimeComparator;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TicketManagerTest {
    @Mock
    private TicketRepository repository;
    @InjectMocks
    private TicketManager manager;
    private Ticket first = new Ticket(1, 9876, "NBC", "KZN", 60);
    private Ticket second = new Ticket(11, 8765, "VKO", "MCX", 240);
    private Ticket third = new Ticket(312, 5676, "SVO", "BVA", 120);
    private Ticket fourth = new Ticket(65, 2345, "MAD", "BGY", 45);
    private Ticket fifth = new Ticket(87, 7567, "MAH", "LIS", 79);
    private Ticket sixth = new Ticket(45, 4567, "NBC", "KZN", 180);
    private Ticket seventh = new Ticket(54, 3456, "MLA", "ALC", 237);
    private Ticket eigth = new Ticket(78, 4456, "MAH", "LIS", 79);
    private Ticket ninth = new Ticket(445, 9786, "NBC", "KZN", 50);
    private Ticket tenth = new Ticket(234, 3456, "MAH", "LIS", 54);

    @Test
    public void shouldCheckSearchByIfMatchesTicketsExist() {
        Ticket[] returned = new Ticket[]{first, second, third, fourth, fifth, sixth, seventh, eigth, ninth, tenth};
        doReturn(returned).when(repository).findAll();
        Ticket[] actual = manager.searchBy("NBC", "KZN", new TicketByTravelTimeComparator());
        Ticket[] expected = new Ticket[]{ninth, first, sixth};
        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }

    @Test
    public void shouldCheckSearchByIfMatchesTicketsExistAndEqualByTime() {
        Ticket[] returned = new Ticket[]{first, second, third, fourth, fifth, sixth, seventh, eigth, ninth, tenth};
        doReturn(returned).when(repository).findAll();
        Ticket[] actual = manager.searchBy("MAH", "LIS", new TicketByTravelTimeComparator());
        Ticket[] expected = new Ticket[]{tenth, fifth, eigth};
        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }

    @Test
    public void shouldCheckSearchByIfMatchesFrom() {
        Ticket[] returned = new Ticket[]{first, second, third, fourth, fifth, sixth, seventh, eigth, ninth, tenth};
        doReturn(returned).when(repository).findAll();
        Ticket[] actual = manager.searchBy("NBC", "ABC", new TicketByTravelTimeComparator());
        Ticket[] expected = new Ticket[0];
        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }

    @Test
    public void shouldCheckSearchByIfMatchesTo() {
        Ticket[] returned = new Ticket[]{first, second, third, fourth, fifth, sixth, seventh, eigth, ninth, tenth};
        doReturn(returned).when(repository).findAll();
        Ticket[] actual = manager.searchBy("ABC", "VKO", new TicketByTravelTimeComparator());
        Ticket[] expected = new Ticket[0];
        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }

    @Test
    public void shouldCheckSearchByIfMatchesTicketsNotExist() {
        Ticket[] returned = new Ticket[]{first, second, third, fourth, fifth, sixth, seventh, eigth, ninth, tenth};
        doReturn(returned).when(repository).findAll();
        Ticket[] actual = manager.searchBy("VKO", "SVO", new TicketByTravelTimeComparator());
        Ticket[] expected = new Ticket[0];
        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }
}